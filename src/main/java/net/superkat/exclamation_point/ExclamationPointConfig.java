package net.superkat.exclamation_point;

import eu.midnightdust.lib.config.MidnightConfig;

public class ExclamationPointConfig extends MidnightConfig {
    @Entry public static boolean showMark = true;
    @Entry public static boolean playMarkSound = true;
    @Entry(name = "Mark Volume", isSlider = true, min = 0f, max = 1f) public static float markSlider = 1.0f;
 }
