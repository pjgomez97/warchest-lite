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

    private final PlayerName name;

    private final List<Unit> bag;

    private final List<Unit> hand;

    private final List<Unit> recruitment;

    private final List<Unit> discard;

    private final List<Token> tokens = new LinkedList<>(List.of(new Token(this), new Token(this), new Token(this), new Token(this)));

    public Player(PlayerName name, List<Unit> bag, List<Unit> recruitment) {
        this.name = name;
        this.bag = bag;
        this.recruitment = recruitment;
        this.hand = new ArrayList<>();
        this.discard = new ArrayList<>();
        initializeHand();
    }

    public PlayerName getName() {
        return name;
    }

    public int getRemainingTokens() {
        return this.tokens.size();
    }

    public void printCurrentStatus() {
        System.out.println();
        System.out.printf("========== %s ==========%n", this.name);
        printHand();
        printRecruitment();
        printDiscard();
    }

    private void printHand() {
        System.out.print("Hand: ");

        for (Unit unit: hand) {
            System.out.print(unit.getType() + ", ");
        }

        System.out.println();
    }

    private void printRecruitment() {
        System.out.print("Recruitment pieces: ");

        Map<UnitType, List<Unit>> recruitmentUnitsByType = this.recruitment.stream().collect(Collectors.groupingBy(Unit::getType));

        for (Map.Entry<UnitType, List<Unit>> entry : recruitmentUnitsByType.entrySet()) {
            System.out.print(entry.getKey() + " = " + entry.getValue().size() + ", ");
        }

        System.out.println();
    }

    private void printDiscard() {
        System.out.print("Discard pile: ");

        for (Unit unit: discard) {
            System.out.print(unit.getType() + ", ");
        }

        System.out.println();
    }

    private void initializeHand() {
        Random random = new Random();

        while (hand.size() < 3) {
            hand.add(bag.remove(random.nextInt(bag.size())));
        }
    }
}
