package chess.model.status;

import chess.model.GameCommand;

public class End implements GameStatus {
    @Override
    public GameStatus changeStatus(GameCommand gameCommand) {
        throw new IllegalStateException("잘못된 상태입력입니다.");
    }
}
