package com.mygdx.viralepidemicsim;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * An abstract class which will be the super class of Young, Young_adult, Adult, and Old classes.
 */
abstract class Person extends SpriteBatch implements PersonInterfaces {
    /**
     * Variables seperated as booleans, bytes and Strings
     */
    private boolean isInfected;
    private boolean mask;
    private boolean inCurfew;

    private byte age;
    private byte immunity;

    private String type;
    private String job;
    private Routine routine; //myRoutine

    //Constructor method setting and creating every instance, also using 3 methods to set a random person.
    public Person() {
        this.isInfected = false;
        this.inCurfew = false;
        this.mask = false;
        randomAgeGenerator();
        typeSetter();
        //jobSetter();
    }
    //Another constructor if it's needed to construct a Young, Young_adult, Adult, or Old class especially.
    public Person(String type) {
        type = type.toUpperCase();
        if(this.getClass().getName() == "YOUNG") {
            this.age = (byte) (Math.random()*FinalVariables.YOUNG_LAST + 1);
        }
        else if(type.equals(FinalVariables.YOUNG_ADULT_NAME)) {
            this.age = (byte) (Math.random()*FinalVariables.YOUNG_ADULT_lAST + 1 - FinalVariables.YOUNG_LAST);
        }
        else if(type.equals(FinalVariables.ADULT_NAME)) {
            this.age = (byte) (Math.random()*FinalVariables.ADULT_lAST + 1 - FinalVariables.YOUNG_ADULT_lAST);
        }
        else /**type.equals(OLD_NAME)*/{
            this.age = (byte) (Math.random()*FinalVariables.OLDEST+ 1 - FinalVariables.ADULT_lAST);
        }
        
        this.isInfected = false;
        this.inCurfew = false;
        this.mask = false;
        typeSetter();
        //jobSetter();
    }
    /**
     * Getter methods for private variables that needs to be checked.
     * @return
     */
    public boolean isIsInfected() {
        return this.isInfected;
    }

    public int getAge() {
        return this.age;
    }

    public int getImmunity() {
        return this.immunity;
    }

    public boolean isMask() {
        return this.mask;
    }

    public boolean isInCurfew() {
        return this.inCurfew;
    }

    public String getType() {
        return this.type;
    }

    public String getJob() {
        return this.job;
    }

    /**
     * Boolean negators
     */
    public void setMask() {
        this.mask = !mask;
    }

    public void setInCurfew() {
        this.inCurfew = !inCurfew;
    }
    
    public void infectOrCure(Person person) {
        this.isInfected = !isInfected;
    }

    public void randomAgeGenerator() {
        this.age = (byte) (Math.random()*100+1);
        //Yaşı oranlara bağlı olarak dağıtmamız lazım. Şimdilik 1 ile 100 arasında rastgele bir değer verdim.
    }

    //Sets the type of the Person according to their age.
    public void typeSetter() {
        if(age <= FinalVariables.YOUNG_LAST) type = FinalVariables.YOUNG;
        else if(age <= FinalVariables.YOUNG_ADULT_lAST) type = FinalVariables.YOUNG_ADULT_NAME;
        else if(age <= FinalVariables.ADULT_lAST) type = FinalVariables.ADULT_NAME;
        else type = FinalVariables.OLD_NAME;
    }

    //According to the Person's age, this method gives a random suitable job to the Person.
    //public void jobSetter() {
    //    if(age < FinalVariables.YOUNG_LAST) this.job = jobs[0];
    //    else if(age < FinalVariables.YOUNG_ADULT_lAST) this.job = jobs[(byte) (Math.random()*6 + 1)];
    //    else if(age < FinalVariables.ADULT_lAST) this.job = jobs[(byte) (Math.random()*5 + 2)];
    //    else this.job = Routine.jobs[7];
    //}

}
