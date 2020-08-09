package com.minimal.todoapp.constants;

import lombok.experimental.UtilityClass;

import java.util.Locale;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@UtilityClass
public class ProjectsConstants {

	public final String DEFAULT_ENCODING = "UTF-8";

	public final Locale TURKISH_LOCALE = new Locale.Builder().setLanguage("tr").setRegion("TR").build();

}
