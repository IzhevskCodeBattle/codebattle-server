package com.codenjoy.dojo.battlecity;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2016 - 2018 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.codenjoy.dojo.battlecity.client.Board;
import com.codenjoy.dojo.battlecity.client.ai.ApofigSolver;
import com.codenjoy.dojo.battlecity.services.GameRunner;
import com.codenjoy.dojo.client.LocalGameRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.RandomDice;
import org.junit.Test;

import javax.print.Doc;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SmokeTest {
    @Test
    public void test() {
        // given
        List<String> messages = new LinkedList<>();

        LocalGameRunner.timeout = 0;
        LocalGameRunner.out = (e) -> messages.add(e);
        LocalGameRunner.countIterations = 15;

        Dice dice = LocalGameRunner.getDice(
                1, 2, 3, 0, 1, 2, 3, 2, // some random numbers
                0, 3, 2, 1, 2, 3, 2, 1,
                2, 3, 2, 1, 2, 3, 1, 2,
                1, 2, 0, 3, 2, 2, 1, 3,
                1, 1, 2, 3, 1, 2, 3, 2,
                1, 2, 3, 2, 3, 2, 1, 2,
                1, 2, 3, 2, 1, 2, 3, 0);

        GameRunner gameType = new GameRunner() {
            @Override
            protected Dice getDice() {
                return dice;
            }

            @Override
            public String getMap() {
                return  "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                        "☼¿ ¿ ¿   ¿ ¿ ¿☼" +
                        "☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼" +
                        "☼ ╬ ╬ ╬☼╬ ╬ ╬ ☼" +
                        "☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼" +
                        "☼ ╬ ╬     ╬ ╬ ☼" +
                        "☼     ╬ ╬     ☼" +
                        "☼☼ ╬╬     ╬╬ ☼☼" +
                        "☼     ╬ ╬     ☼" +
                        "☼ ╬ ╬ ╬╬╬ ╬ ╬ ☼" +
                        "☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼" +
                        "☼ ╬         ╬ ☼" +
                        "☼ ╬   ╬╬╬   ╬ ☼" +
                        "☼     ╬ ╬     ☼" +
                        "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼";
            }
        };

        // when
        LocalGameRunner.run(gameType,
                new ApofigSolver(null),
                new Board());

        // then
        assertEquals("DICE:1\n" +
                        "DICE:2\n" +
                        "1:Board:\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:☼¿ ¿ ¿   ¿ ¿ ¿☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬☼╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬     ╬ ╬ ☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼☼ ╬╬     ╬╬ ☼☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼ ╬ ╬ ╬╬╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬         ╬ ☼\n" +
                        "1:☼▲╬   ╬╬╬   ╬ ☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:\n" +
                        "1:Answer: UP, ACT\n" +
                        "------------------------------------------\n" +
                        "1:Board:\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:☼             ☼\n" +
                        "1:☼¿╬¿╬¿╬ ╬¿╬¿╬¿☼\n" +
                        "1:☼•╬•╬•╬☼╬•╬•╬•☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬     ╬ ╬ ☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼☼ ╬╬     ╬╬ ☼☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼ ╬ ╬ ╬╬╬ ╬ ╬ ☼\n" +
                        "1:☼•╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼▲╬         ╬ ☼\n" +
                        "1:☼ ╬   ╬╬╬   ╬ ☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:\n" +
                        "1:Answer: ACT\n" +
                        "------------------------------------------\n" +
                        "1:Board:\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:☼             ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼¿╬¿╬¿╬☼╬¿╬¿╬¿☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼•╬•╬•   •╬•╬•☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼☼ ╬╬     ╬╬ ☼☼\n" +
                        "1:☼•    ╬ ╬     ☼\n" +
                        "1:☼ ╬ ╬ ╬╬╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼▲╬         ╬ ☼\n" +
                        "1:☼ ╬   ╬╬╬   ╬ ☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:\n" +
                        "1:Answer: ACT\n" +
                        "------------------------------------------\n" +
                        "1:Board:\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:☼             ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬☼╬ ╬ ╬ ☼\n" +
                        "1:☼¿╬¿╬¿╬ ╬¿╬¿╬¿☼\n" +
                        "1:☼ ╬ ╬     ╬ ╬ ☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼☼ ╦╬•   •╬╦ ☼☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼ ╬ ╬ ╬╬╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼▲╬         ╬ ☼\n" +
                        "1:☼ ╬   ╬╬╬   ╬ ☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:\n" +
                        "1:Answer: UP, ACT\n" +
                        "------------------------------------------\n" +
                        "1:Board:\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:☼             ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬☼╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼¿╬¿╬¿   ¿╬¿╬¿☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼☼ ╦╬     ╬╦ ☼☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼ ╬ ╬•╬╬╬•╬ ╬ ☼\n" +
                        "1:☼▲╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬         ╬ ☼\n" +
                        "1:☼ ╬   ╬╬╬   ╬ ☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:\n" +
                        "1:Answer: UP, ACT\n" +
                        "------------------------------------------\n" +
                        "1:Board:\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:☼             ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬☼╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬     ╬ ╬ ☼\n" +
                        "1:☼¿ ¿ ¿╬ ╬¿ ¿ ¿☼\n" +
                        "1:☼☼ ╦╬     ╬╦ ☼☼\n" +
                        "1:☼•    ╬ ╬     ☼\n" +
                        "1:☼▲╬ ╬ ╬╬╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬  •   •  ╬ ☼\n" +
                        "1:☼ ╬   ╬╬╬   ╬ ☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:\n" +
                        "1:Answer: DOWN, ACT\n" +
                        "DICE:3\n" +
                        "DICE:0\n" +
                        "DICE:1\n" +
                        "DICE:2\n" +
                        "DICE:3\n" +
                        "DICE:2\n" +
                        "DICE:0\n" +
                        "------------------------------------------\n" +
                        "1:Board:\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:☼             ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬☼╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬?╬     ╬?╬ ☼\n" +
                        "1:☼ »   ╬ ╬   « ☼\n" +
                        "1:☼☼ ╦╬¿   ¿╬╦ ☼☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼ ╬ ╬ ╬╬╬ ╬ ╬ ☼\n" +
                        "1:☼▼╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬         ╬ ☼\n" +
                        "1:☼ ╬   ╬╬╬   ╬ ☼\n" +
                        "1:☼    •╬ ╬•    ☼\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:\n" +
                        "1:Answer: UP, ACT\n" +
                        "------------------------------------------\n" +
                        "1:Board:\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:☼             ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬☼╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬?╬ ╬ ╬ ╬?╬ ☼\n" +
                        "1:☼ ╬ ╬     ╬ ╬ ☼\n" +
                        "1:☼  »  ╬ ╬  «  ☼\n" +
                        "1:☼☼ ╦╬     ╬╦ ☼☼\n" +
                        "1:☼    ¿╬ ╬¿    ☼\n" +
                        "1:☼▲╬ ╬ ╬╬╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬         ╬ ☼\n" +
                        "1:☼ ╬   ╬╬╬   ╬ ☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:\n" +
                        "1:Answer: UP, ACT\n" +
                        "------------------------------------------\n" +
                        "1:Board:\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:☼             ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬?╬ ╬☼╬ ╬?╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬     ╬ ╬ ☼\n" +
                        "1:☼   » ╬ ╬ «   ☼\n" +
                        "1:☼☼ ╦╬     ╬╦ ☼☼\n" +
                        "1:☼▲    ╬ ╬     ☼\n" +
                        "1:☼ ╬ ╬¿╬╬╬¿╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬         ╬ ☼\n" +
                        "1:☼ ╬   ╬╬╬   ╬ ☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:\n" +
                        "1:Answer: RIGHT, ACT\n" +
                        "------------------------------------------\n" +
                        "1:Board:\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:☼             ☼\n" +
                        "1:☼ ╬?╬ ╬ ╬ ╬?╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬☼╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬     ╬ ╬ ☼\n" +
                        "1:☼    »╬ ╬«    ☼\n" +
                        "1:☼☼ ╦╬     ╬╦ ☼☼\n" +
                        "1:☼ ►•  ╬ ╬     ☼\n" +
                        "1:☼ ╬ ╬ ╬╬╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬¿╬ ╬¿╬ ╬ ☼\n" +
                        "1:☼ ╬         ╬ ☼\n" +
                        "1:☼ ╬   ╬╬╬   ╬ ☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:\n" +
                        "1:Answer: UP, ACT\n" +
                        "DICE:3\n" +
                        "DICE:2\n" +
                        "------------------------------------------\n" +
                        "1:Board:\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:☼  ?       ?  ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬☼╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬    ?╬ ╬ ☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼☼▲╦╬¿    ╬╦ ☼☼\n" +
                        "1:☼    •╬ ╬     ☼\n" +
                        "1:☼ ╬ ╬ ╬╬╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬  ¿   ¿  ╬ ☼\n" +
                        "1:☼ ╬   ╬╬╬   ╬ ☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:\n" +
                        "1:Answer: UP, ACT\n" +
                        "Fire Event: KILL_OTHER_TANK\n" +
                        "DICE:1\n" +
                        "DICE:2\n" +
                        "DICE:3\n" +
                        "------------------------------------------\n" +
                        "1:Board:\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:☼   »         ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬¿╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬☼╬•╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬?╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬     ╬ ╬ ☼\n" +
                        "1:☼ ▲   ╬ ╬     ☼\n" +
                        "1:☼☼ ╦╬     ╬╦ ☼☼\n" +
                        "1:☼    Ѡ╬ ╬     ☼\n" +
                        "1:☼ ╬ ╬•╬╬╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬         ╬ ☼\n" +
                        "1:☼ ╬  ¿╬╬╬¿  ╬ ☼\n" +
                        "1:☼    •╬ ╬•    ☼\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:\n" +
                        "1:Answer: RIGHT, ACT\n" +
                        "DICE:2\n" +
                        "DICE:1\n" +
                        "------------------------------------------\n" +
                        "1:Board:\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:☼  » »   •    ☼\n" +
                        "1:☼ ╦ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬☼╬?╬¿╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬     ╬ ╬ ☼\n" +
                        "1:☼  ►  ╬ ╬     ☼\n" +
                        "1:☼☼ ╦╬     ╬╦ ☼☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼ ╬ ╬ ╬╬╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬         ╬ ☼\n" +
                        "1:☼ ╬   ╬╬╬   ╬ ☼\n" +
                        "1:☼    ¿╬ ╬¿    ☼\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:\n" +
                        "1:Answer: UP, ACT\n" +
                        "DICE:2\n" +
                        "DICE:3\n" +
                        "DICE:2\n" +
                        "------------------------------------------\n" +
                        "1:Board:\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:☼   » »       ☼\n" +
                        "1:☼ ╦ ╬ ╬ ╬?╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬☼╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬•╬ ╬ ╬ ╬¿╬ ☼\n" +
                        "1:☼ ╬▲╬     ╬ ╬ ☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼☼ ╦╬     ╬╦ ☼☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼ ╬ ╬ ╬╬╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬         ╬ ☼\n" +
                        "1:☼ ╬  ?╬╬╬?  ╬ ☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:\n" +
                        "1:Answer: DOWN, ACT\n" +
                        "------------------------------------------\n" +
                        "1:Board:\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:☼    » » ?    ☼\n" +
                        "1:☼ ╦•╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬☼╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬     ╬¿╬ ☼\n" +
                        "1:☼  ▼  ╬ ╬     ☼\n" +
                        "1:☼☼ ╦╬     ╬╦ ☼☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼ ╬ ╬ ╬╬╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬ ╬ ╬ ╬ ╬ ╬ ☼\n" +
                        "1:☼ ╬  ?   ?  ╬ ☼\n" +
                        "1:☼ ╬   ╬╬╬   ╬ ☼\n" +
                        "1:☼     ╬ ╬     ☼\n" +
                        "1:☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼\n" +
                        "1:\n" +
                        "1:Answer: RIGHT, ACT\n" +
                        "DICE:1\n" +
                        "------------------------------------------",
                String.join("\n", messages));

    }
}