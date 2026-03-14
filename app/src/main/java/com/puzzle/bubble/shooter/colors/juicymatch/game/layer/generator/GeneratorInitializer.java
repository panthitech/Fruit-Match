package com.puzzle.bubble.shooter.colors.juicymatch.game.layer.generator;

import com.puzzle.bubble.shooter.colors.juicymatch.asset.Textures;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.generator.type.ColumnStripedGenerator;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.generator.type.ExplosiveGenerator;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.generator.type.RowColumnStripedGenerator;
import com.puzzle.bubble.shooter.colors.juicymatch.game.layer.generator.type.RowStripedGenerator;
import com.nativegame.natyengine.engine.Engine;


public class GeneratorInitializer {

    public static Generator getGenerator(Engine engine, char c) {
        switch (c) {
            case 'R':
                return new RowStripedGenerator(engine, Textures.GENERATOR_ROW_STRIPED);
            case 'C':
                return new ColumnStripedGenerator(engine, Textures.GENERATOR_COLUMN_STRIPED);
            case 'M':
                return new RowColumnStripedGenerator(engine, Textures.GENERATOR_ROW_COLUMN_STRIPED);
            case 'E':
                return new ExplosiveGenerator(engine, Textures.GENERATOR_EXPLOSIVE);
            default:
                throw new IllegalArgumentException("Generator not found!");
        }
    }

}
