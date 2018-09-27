/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2016 Codenjoy
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
function initLeadersTable(contextPath, playerName, code, onDrawItem, onParseValue){

    var leaderboard = $("#leaderboard");
    leaderboard.show();

    function getFirstValue(data) {
        return data[Object.keys(data)[0]];
    }

    function sortByGameScore(gameName, data) {
        var vals = new Array();

        for (i in data) {
            vals.push([i, data[i]])
        }

        var sortFunc;
        if (gameName === 'battlecity') {
            sortFunc = sortByKillsDeaths;
        } else {
            sortFunc = sortByScores;
        }

        vals = vals.sort(function(a, b) {
            return sortFunc(a[1], b[1]);
        });

        var result = new Object();

        for (i in vals) {
            result[vals[i][0]] = vals[i][1];
        }

        return result;
    }

    function sortByKillsDeaths(scoreOne, scoreTwo) {
        return scoreTwo.kills - scoreOne.kills || scoreOne.deaths - scoreTwo.deaths;
    }

    function sortByScores(scoreOne, scoreTwo) {
        return scoreTwo.score - scoreOne.score;
    }

    function drawLeaderTable(data) {
        if (data == null) {
            $("#table-logs-body").empty();
            return;
        }
        data = getFirstValue(data);

        var gameName = data.gameName;

        data = data.scores;
        if (data == null) {
            $("#table-logs-body").empty();
            return;
        }

        data = sortByGameScore(gameName, data);

        if (!onDrawItem) {
            onDrawItem = function(count, you, link, name, scores) {
                return '<tr>' +
                        '<td>' + count + '</td>' +
                        '<td>' + you + '<a href="' + link + '">' + name + '</a></td>' +
                        '<td class="center">' + getScoreForGame(gameName, scores) + '</td>' +
                    '</tr>';
            }
        }

        var tbody = '';
        var count = 0;
        $.each(data, function (email, score) {
            var name = email.substring(0, email.indexOf('@'));
            if (name == 'chatLog') {
                return;
            }

            var you = (name == playerName)?"=> ":"";

            count++;
            var link = contextPath + '/board/player/' + email + ((!!code)?('?code=' + code):"");
            tbody += onDrawItem(count, you, link, name, score);

        });

        $("#table-logs-body").empty().append(tbody);
        leaderboard.trigger($.Event('resize'));
    }

    function getScoreForGame(gameName, scores) {
        if (gameName === 'battlecity') {
            return scores.kills + '/' + scores.deaths;
        } else {
            return scores.score;
        }
    }

    function isEmpty(map) {
       for (var key in map) {
          if (map.hasOwnProperty(key)) {
             return false;
          }
       }
       return true;
    }

    $('body').bind("board-updated", function(event, data) {
        if (!isEmpty(data)) {
            drawLeaderTable(data);
        }
    });
};
