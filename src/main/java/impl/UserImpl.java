package impl;

import domain.User;

public class UserImpl implements User {
    private static final long WELCOME_BONUS = 1000;
    private final String name;
    private long money;
    private int winstreak;

    public UserImpl(String name) {
        this.name = name;
        this.money = WELCOME_BONUS;
        this.winstreak = 0;
    }

    @Override
    public void winSreak(int winstreak) {
        this.winstreak += winstreak;
    }

    @Override
    public int getWinStreak() {
        return this.winstreak;
    }

    @Override
    public void increaseMoney(long amount) {
        this.money += amount;
    }

    @Override
    public void reduceMoney(long amount) {
        this.money -= amount;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getMoney() {
        return this.money;
    }


}
