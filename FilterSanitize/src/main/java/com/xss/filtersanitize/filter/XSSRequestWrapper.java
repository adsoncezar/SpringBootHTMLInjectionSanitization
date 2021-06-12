package com.xss.filtersanitize.filter;

import static com.xss.filtersanitize.Utils.SanitizerUtils.clearXss;

import com.xss.filtersanitize.Utils.SanitizerUtils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XSSRequestWrapper extends HttpServletRequestWrapper {

  public XSSRequestWrapper(HttpServletRequest request) {
    super(request);
  }

  @Override
  public String[] getParameterValues(String parameter) {
    var values = super.getParameterValues(parameter);
    if (values == null)
      return null;

    var count = values.length;
    var encodedValues = new String[count];
    for (int i = 0; i < count; i++) {
      encodedValues[i] = clearXss(values[i]);
    }
    return encodedValues;
  }

  @Override
  public String getParameter(String parameter) {
    String value = super.getParameter(parameter);
    return value != null ? clearXss(value) : null;
  }

  @Override
  public String getHeader(String name) {
    var value = super.getHeader(name);
    return value != null ? clearXss(value) : null;
  }

  @Override
  public Enumeration<String> getHeaders(String name) {
    List<String> result = new ArrayList<>();
    var headers = super.getHeaders(name);
    while (headers.hasMoreElements()) {
      var header = headers.nextElement();
      var tokens = header.split(",");
      Arrays.stream(tokens).map(SanitizerUtils::clearXss).forEach(result::add);
    }

    return Collections.enumeration(result);
  }


  @Override
  public BufferedReader getReader() throws IOException {
    return new BufferedReader(new InputStreamReader(getInputStream()));
  }


  @Override
  public ServletInputStream getInputStream() throws IOException {
    final var byteArrayInputStream = new ByteArrayInputStream(
        sanitizerBodyInputStream(super.getInputStream()).getBytes());

    return new ServletInputStream() {

      @Override
      public int read() throws IOException {
        return byteArrayInputStream.read();
      }

      @Override
      public boolean isFinished() {
        return false;
      }

      @Override
      public boolean isReady() {
        return false;
      }

      @Override
      public void setReadListener(ReadListener readListener) {
      }
    };
  }

  private String sanitizerBodyInputStream(InputStream inputStream) {
    var body = new StringBuilder();

    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(inputStream, Charset.defaultCharset()))) {
      String line;
      while ((line = reader.readLine()) != null) {
        body.append(line);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return clearXss(body.toString());
  }
}
