package com.mygdx.viralepidemicsim;

public interface FinalVariables {
    /**
     * Ages and their interval separated to age groups.
     * https://www.researchgate.net/figure/Age-intervals-and-age-groups_tbl1_228404297
     */
    final byte YOUNG_LAST = 18;
    final byte YOUNG_ADULT_lAST = 39;
    final byte ADULT_lAST = 59;
    final byte OLDEST = 99;

    /**
     * Immunity level percentage and their change over time.
     * http://www.h-ls.jp/measurement07e.html
     */
    final byte YOUNG_ADULT_IMMUNITY = 88;
    final byte YOUNG_ADULT_IMMUNITY_LAST = 76;
    final byte ADULT_IMMUNITY = 75;
    final byte ADULT_IMMUNITY_LAST = 22;
    final byte OLD_IMMUNITY = 20;
    final byte OLDEST_IMMUNITY = 6;

    final byte SPREAD_RADIUS = 5;

    /**
     * Age group percentage around the world.
     * https://www.visualcapitalist.com/the-worlds-population-2020-by-age/
     */
    final float YOUNG_PERCENTAGE = (float) 33.2;
    final float YOUNG_ADULT_PERCENTAGE = (float) 29.9;
    final float ADULT_PERCENTAGE = (float) 23.1;
    final float OLD_PERCENTAGE = (float) 13.71;

    /**
     * Age groups as string
     */
    final String YOUNG = "Young";
    final String YOUNG_ADULT_NAME = "Young adult";
    final String ADULT_NAME = "Adult";
    final String OLD_NAME = "Old";
}
