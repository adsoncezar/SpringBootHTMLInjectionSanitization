package com.xss.filtersanitize.Utils;

import lombok.experimental.UtilityClass;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

@UtilityClass
public class SanitizerUtils {

  private static final PolicyFactory POLICY_FACTORY = new HtmlPolicyBuilder().toFactory();

  public static String clearXss(String value) {
    var s = POLICY_FACTORY.sanitize(value);
    return Jsoup.clean(s, Whitelist.none());
  }

}
