package org.warchest.player;

import org.warchest.unit.Unit;
import org.warchest.unit.UnitType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Player {

    private final List<Unit> bag;

    private final List<Unit> hand;

    private final List<Unit> recruitment;

    private final List<Unit> discard;

    private final List<Token> tokens = new LinkedList<>(List.of(new Token(this), new Token(this), new Token(this), new Token(this)));

    public Player(List<Unit> bag, List<Unit> recruitment) {
        this.bag = bag;
        this.recruitment = recruitment;
        this.hand = new ArrayList<>();
        this.discard = new ArrayList<>();
        initializeHand();
    }

    public int getRemainingTokens() {
        return tokens.size();
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

    public void initializeHand() {
        Random random = new Random();

        while (hand.size() < 3) {
            hand.add(bag.remove(random.nextInt(bag.size())));
        }
    }

    public Unit getUnitFromHandByType(UnitType unitType) {
        return hand.stream().filter(unit -> unit.getType() == unitType).findFirst().get();
    }
}
