package io.indices.hideandseek;

import javax.annotation.Nonnull;

import com.voxelgameslib.voxelgameslib.game.AbstractGame;
import com.voxelgameslib.voxelgameslib.game.GameDefinition;
import com.voxelgameslib.voxelgameslib.game.GameInfo;
import com.voxelgameslib.voxelgameslib.phase.phases.LobbyWithVotePhase;

import io.indices.hideandseek.phases.ActivePhase;
import io.indices.hideandseek.phases.GracePhase;

@GameInfo(name = "HideAndSeek", author = "aphel", version = "1.0", description = "Everyone knows hide and seek!")
public class HideAndSeekGame extends AbstractGame {

    public HideAndSeekGame() {
        super(HideAndSeekPlugin.GAMEMODE);
    }

    @Override
    public void initGameFromModule() {
        setMinPlayers(2); // todo raise for production
        setMaxPlayers(2);

        LobbyWithVotePhase lobbyPhase = createPhase(LobbyWithVotePhase.class);
        GracePhase hidingPhase = createPhase(GracePhase.class);
        ActivePhase mainPhase = createPhase(ActivePhase.class);

        lobbyPhase.setNextPhase(hidingPhase);
        hidingPhase.setNextPhase(mainPhase);

        activePhase = lobbyPhase;

        loadMap();
    }

    @Override
    public void initGameFromDefinition(@Nonnull GameDefinition gameDefinition) {
        super.initGameFromDefinition(gameDefinition);

        loadMap();
    }
}
