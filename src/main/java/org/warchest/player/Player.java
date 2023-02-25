package org.warchest.player;

import org.warchest.unit.Unit;
import org.warchest.unit.UnitType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Player {

    private final PlayerName playerName;

    private final List<Unit> bag;

    private final List<Unit> hand;

    private final List<Unit> recruitment;

    private final List<Unit> discard;

    private int tokens = 4;

    public Player(PlayerName playerName, List<Unit> bag, List<Unit> recruitment) {
        this.playerName = playerName;
        this.bag = bag;
        this.recruitment = recruitment;
        this.hand = new ArrayList<>();
        this.discard = new ArrayList<>();
        setOwnership();
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public boolean hasEmptyBag() {
        return bag.isEmpty();
    }

    public boolean hasEmptyHand() {
        return hand.isEmpty();
    }

    public boolean hasNoUnitsToRecruit() {
        return recruitment.isEmpty();
    }

    public int getRemainingTokens() {
        return tokens;
    }

    public void addToken() {
        tokens++;
    }

    public void removeToken() {
        tokens--;
    }

    public void printHand() {
        System.out.print("Hand: ");

        for (Unit unit: hand) {
            System.out.print(unit.getType() + ", ");
        }

        System.out.println();
    }

    public void printRecruitment() {
        System.out.print("Recruitment pieces: ");

        Map<UnitType, List<Unit>> recruitmentUnitsByType = recruitment.stream().collect(Collectors.groupingBy(Unit::getType));

        for (Map.Entry<UnitType, List<Unit>> entry : recruitmentUnitsByType.entrySet()) {
            System.out.print(entry.getKey() + " = " + entry.getValue().size() + ", ");
        }

        System.out.println();
    }

    public void printDiscard() {
        System.out.print("Discard pile: ");

        for (Unit unit: discard) {
            System.out.print(unit.getType() + ", ");
        }

        System.out.println();
    }

    public void setOwnership() {
        for (Unit unit: bag) {
            unit.setOwner(this);
        }
        for (Unit unit: recruitment) {
            unit.setOwner(this);
        }
    }

    public void initializeHand() {
        Random random = new Random();

        while (hand.size() < 3) {
            hand.add(bag.remove(random.nextInt(bag.size())));
        }
    }

    public Unit getUnitFromHandByType(UnitType unitType) {
        return hand.stream().filter(unit -> unit.getType() == unitType).findFirst().orElse(null);
    }

    public void removeUnitFromHand(Unit unit) {
        hand.remove(unit);
    }

    public void discardUnitFromHand(Unit unit) {
        hand.remove(unit);
        discard.add(unit);
    }

    public void recruitUnit(Unit handUnit, Unit recruitmentUnit) {
        discardUnitFromHand(handUnit);
        recruitment.remove(recruitmentUnit);
        bag.add(recruitmentUnit);
    }

    public Unit getUnitFromRecruitmentByType(UnitType unitType) {
        return recruitment.stream().filter(unit -> unit.getType() == unitType).findFirst().orElse(null);
    }
}
