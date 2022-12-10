package net.superkat.exclamation_point;

import eu.midnightdust.lib.config.MidnightConfig;

public class ExclamationPointConfig extends MidnightConfig {
    @Entry public static boolean modEnabled = true;

    @Entry public static boolean showExclamationMark = true;
    @Entry public static boolean playExclamationMarkSound = true;
    @Entry(name = "Exclamation Point Volume", isSlider = true, min = 0f, max = 1f) public static float exclamationMarkVolume = 1.0f;

//    @Entry public static boolean showQuestionMark = true;
//    @Entry public static boolean playQuestionMarkSound = true;
//    @Entry(name = "Question Mark Volume", isSlider = true, min = 0f, max = 1f) public static float questionMarkSlider = 1.0f;
 }
