package com.epam.gm.web.servlets.local;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.StringJoiner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.epam.gm.model.Language;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.util.CookieUtil;
import com.epam.gm.web.servlets.frontcontroller.FrontUtil;

public class ShowLocaleTag extends TagSupport {
	private static final String MAIN_SCRIPT =

	"<script> "
			+ "$(document).ready(function () { \n"
			+ "    $('[data-country-select=true]').countrySelector({ \n"
			+ "        countries: _langs_, \n"
			+ "        value: '_current_', \n"
			+ "        onSelected: function (country, fullname) { \n"
			+ "        	window.location.href = 'local.do?lang=' + country + '&from=_from_' \n"
			+ "        } " + "    }); " + " }); " + " </script> \n";

	private static final String FRONTEND_LOCALE_SCRIPT = " <script type='text/javascript'> \n"
			+ " function _(str, locale) { \n"
			+ "    locale = locale || _.defaultLocale; \n"
			+ "    if (_.data.hasOwnProperty(locale) && typeof _.data[locale] == 'object') { \n"
			+ "        if (_.data[locale].hasOwnProperty(str)) { \n"
			+ "            return _.data[locale][str]; \n"
			+ "        } \n"
			+ "    } \n"
			+ "    return str; \n"
			+ " } \n"
			+

			" _.defaultLocale = 'default'; \n"
			+ " _.data = { \n"
			+ "        ru: {} \n"
			+ " }; \n"
			+ "_.registerLocale = function registerLocale(locale, data) { \n"
			+ "    if (!_.data.hasOwnProperty(locale)) { \n"
			+ "        _.data[locale] = {}; \n"
			+ "    } \n"
			+ "    for (var str in data) { \n"
			+ "        if (data.hasOwnProperty(str)) { \n"
			+ "            _.data[locale][str] = data[str]; \n"
			+ "        } \n"
			+ "    } \n"
			+ "} \n"
			+

			"_.registerLocale('default', { \n"
			+ "    'nothing': '', \n"
			+ "    _dictionary_ \n" + " }); \n" + 
			"</script> \n";

	private String mBasename;

	private String mFrom;

	public void setBasename(String pBasename) {
		mBasename = pBasename;
	}

	public void setFrom(String pFrom) {
		mFrom = pFrom;
	}

	public int doStartTag() throws JspException {
		try {
			@SuppressWarnings("unchecked")
			List<Language> applicationLangs = (List<Language>) pageContext
					.getRequest().getServletContext()
					.getAttribute("applicationLangs");

			HttpServletRequest request = (HttpServletRequest) pageContext
					.getRequest();

			JspWriter out = pageContext.getOut();

			StringJoiner joiner = new StringJoiner(", ", "[", "]");
			joiner.setEmptyValue("['GBR']");

			if (applicationLangs != null) {
				Language language = SessionRepository
						.getSessionLanguage(request);

				if (language == null) {
					language = CookieUtil.getLastLanguageFromCookie(request);

				}

				if (language != null) {
					SessionRepository.setSessionLanguage(request, language);
				} else {
					SessionRepository.setSessionLanguage(request,
							applicationLangs.get(0));
				}

				String key = SessionRepository.getSessionLanguage(request)
						.getKey();
				String locale = SessionRepository.getSessionLanguage(request)
						.getLocale();

				System.out
						.println("--------------------------------------------------------locale ="
								+ locale);

				for (Language l : applicationLangs) {
					joiner.add("'" + l.getKey() + "'");
				}

				
				//System.out.println("**************************************************** From:" + request.getRequestURI());
				
				String script = MAIN_SCRIPT
						.replaceAll("_langs_", joiner.toString())
						.replaceAll("_current_", key)
						.replaceAll("_from_", mFrom);
				out.println(script);

				//System.out.println("Lang script: " + script);
				out.println(getLocaleForJS(locale));

			}

		} catch (Exception e) {
			e.printStackTrace();

			try {

				HttpServletResponse response = (HttpServletResponse) pageContext
						.getResponse();
				response.sendRedirect("404.do");

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		return SKIP_BODY;
	}

	public String getLocaleForJS(String locale) {
		String res = "";
		Properties props = new Properties();
		FileInputStream proStr = null;

		// String propsFile =
		// getClass().getClassLoader().getResource("locale/home/messages_ua_UA.properties").getPath();

		String bundlePath = mBasename.replaceAll("\\.", "/") + "_" + locale
				+ ".properties";

		System.out.println("bundlePath = " + bundlePath);

		java.net.URL url = getClass().getClassLoader().getResource(bundlePath);

		StringJoiner joiner = new StringJoiner(", \n", "", "");
		joiner.setEmptyValue("");

		if (url != null) {

			String propsFile = getClass().getClassLoader()
					.getResource(bundlePath).getPath();

			StringBuilder builder = new StringBuilder();

			try {
				proStr = new FileInputStream(propsFile);
				props.load(proStr);
				Enumeration<?> enKeys = props.propertyNames();
				while (enKeys.hasMoreElements()) {
					String key = (String) enKeys.nextElement();
					String name = props.getProperty(key);

					if (key == null || name == null)
						continue;
					
					//name = name.replaceAll("'", "&rsquo;");

					if (key.startsWith("js.")) {

//						joiner.add(builder.append("'").append(key.trim())
//								.append("' : '").append(name.trim())
//								.append("'").toString());
						
						joiner.add(builder.append("\"").append(key.trim())
								.append("\" : \"").append(name.trim())
								.append("\"").toString());						

						builder.setLength(0);
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
				;
			} finally {
				try {
					proStr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("NO BUNDLE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}

		res = FRONTEND_LOCALE_SCRIPT.replace("_dictionary_", joiner.toString());

		//System.out
		//		.println("Our script: *************************************************");
		//System.out.println(res);

		return res;
	}

}
