package step2.view;

import static java.lang.System.*;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

import step2.domain.Results;
import step2.domain.ladder.Direction;
import step2.domain.ladder.Ladder;
import step2.domain.ladder.Line;
import step2.domain.player.Players;

public class OutputView {

	private static final String LINE_BREAK = "\n";
	private static final Map<Direction, String> DIRECTION_PRINT_MAP = new EnumMap<>(Direction.class);

	static {
		DIRECTION_PRINT_MAP.put(Direction.LEFT, "--|  ");
		DIRECTION_PRINT_MAP.put(Direction.STRAIGHT, "  |  ");
		DIRECTION_PRINT_MAP.put(Direction.RIGHT, "  |--");
	}

	public void showCreationResult() {
		show(LINE_BREAK);
		show("사다리 결과");
		show(LINE_BREAK);
	}

	public void showPlayersResult(Players players) {
		String result = players.getValues()
			.stream()
			.map(player -> format(player.getName()))
			.collect(Collectors.joining());
		show(result);
	}

	public void showLadderResult(Ladder ladder) {
		String result = ladder.getLines().stream()
			.map(this::mapToConsolePrint)
			.collect(Collectors.joining(LINE_BREAK));
		show(result);
	}

	private String mapToConsolePrint(Line line) {
		return line.getPoints()
			.stream()
			.map(point -> DIRECTION_PRINT_MAP.get(point.getDirection()))
			.collect(Collectors.joining());
	}

	public void showResults(Results results) {
		String result = results.getValues()
			.stream()
			.map(this::format)
			.collect(Collectors.joining());
		show(result);
	}

	public void showAllResult() {
		show(LINE_BREAK);
		show("<<실행결과>>");
	}

	public void showResultWithName(String playerName, String result) {
		show(String.format("%s : %s", playerName, result));
	}

	private String format(String input) {
		return String.format("%-5s", input);
	}

	public void show(String output) {
		out.println(output);
	}
}
