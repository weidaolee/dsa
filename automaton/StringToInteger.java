package automaton;

import java.util.HashMap;
import java.util.Map;

public class StringToInteger {
    /**
     * Link:
     * https://leetcode-cn.com/problems/string-to-integer-atoi/
     *
     */
    public int myAtoi(String s) {
        Automaton automaton = new Automaton();
        for (int i = 0; i < s.length(); i++) {
            automaton.drive(s.charAt(i));
        }
        if (automaton.ans == Integer.MIN_VALUE) {
            if (automaton.sign == 1) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        } else {
            if (automaton.sign == 1) {
                return automaton.ans * -1;
            } else {
                return automaton.ans;
            }
        }
    }

    private static class Automaton {
        public int sign;
        public int ans;

        private int minq;
        private int minr;

        private State state;
        private Map <State, Map<Condition, State>> map;

        public Automaton () {
            sign = 1;
            ans = 0;
            minq = Integer.MIN_VALUE / 10;
            minr = Integer.MIN_VALUE % 10;

            state = State.START;
            map = new HashMap<>();
            for (State s : State.values()) {
                map.put(s, new HashMap<>());
            }

            map.get(State.START).put(Condition.SPACE, State.START);
            map.get(State.START).put(Condition.SIGN, State.SIGNED);
            map.get(State.START).put(Condition.DIGIT, State.IN_NUMBER);
            map.get(State.START).put(Condition.OTHERS, State.END);

            map.get(State.SIGNED).put(Condition.SPACE, State.END);
            map.get(State.SIGNED).put(Condition.SIGN, State.END);
            map.get(State.SIGNED).put(Condition.DIGIT, State.IN_NUMBER);
            map.get(State.SIGNED).put(Condition.OTHERS, State.END);

            map.get(State.IN_NUMBER).put(Condition.SPACE, State.END);
            map.get(State.IN_NUMBER).put(Condition.SIGN, State.END);
            map.get(State.IN_NUMBER).put(Condition.DIGIT, State.IN_NUMBER);
            map.get(State.IN_NUMBER).put(Condition.OTHERS, State.END);

            map.get(State.END).put(Condition.SPACE, State.END);
            map.get(State.END).put(Condition.SIGN, State.END);
            map.get(State.END).put(Condition.DIGIT, State.END);
            map.get(State.END).put(Condition.OTHERS, State.END);
        }

        public Condition getCondition (char c) {
            if (c == ' ') {
                return Condition.SPACE;
            } else if (c == '-' || c == '+') {
                return Condition.SIGN;
            } else if (Character.isDigit(c)) {
                return Condition.DIGIT;
            } else {
                return Condition.OTHERS;
            }
        }

        public void drive (char c) {
            state = map.get(state).get(getCondition(c));
            if (state == State.IN_NUMBER) {
                if ((ans < minq) || (ans == minq && '0' - c < minr)) {
                    ans = Integer.MIN_VALUE;
                } else {
                    ans = ans * 10 + '0' - c;
                }
            } else if (state == State.SIGNED) {
                if (c == '-') {
                    sign = -1;
                }
            }
        }
    }
    
    private enum State {
        START,
        SIGNED,
        IN_NUMBER,
        END
    }

    private enum Condition {
        SPACE,
        SIGN,
        DIGIT,
        OTHERS
    }
}
