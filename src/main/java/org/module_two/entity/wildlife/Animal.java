package org.module_two.entity.wildlife;

public interface Animal {
    public int getHp();
    public void setHp(int newHp);
    public boolean eat(Object o, int indexWLocation, int indexHLocation);
    public void reproduce(int indexWLocation, int indexHLocation);
    public void movementByLocations(int indexWLocation, int indexHLocation);
    public void fillTableProbabilityEat();
}
