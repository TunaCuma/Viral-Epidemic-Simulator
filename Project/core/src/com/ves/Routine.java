package com.ves;

/**
 * An abstract class which Person class will inherit and benefit from.
 */
abstract class Routine implements FinalVariables{
    String[] jobs;
    public Routine() {
        jobs = new String[8];
        jobs[0] = "No jobs";
        jobs[1] = "Student";
        jobs[2] = "Doctor";
        jobs[3] = "Laborer";
        jobs[4] = "Cashier";
        jobs[5] = "Officer";
        jobs[6] = "Janitor";
        jobs[7] = "Retired";
    }
}
