package pl.horus.recruitment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Wall wall = new Wall();
        List<Block> blocks = new ArrayList<>();
        blocks.add(new SimpleBlock("Red", "Brick"));
        blocks.add(new SimpleBlock("Blue", "Concrete"));
        blocks.add(new CompositeBlockImpl(
                List.of(
                        new SimpleBlock("Green", "Wood"),
                        new SimpleBlock("Yellow", "Brick"),
                        new CompositeBlockImpl(
                                List.of(
                                        new SimpleBlock("White", "Concrete"),
                                        new SimpleBlock("Black", "Wood")
                                )
                        )
                )
        ));
        wall.setBlocks(blocks);


        Optional<Block> foundBlock = wall.findBlockByColor("Multi-colored");

        if (foundBlock.isPresent()) {
            System.out.println(String.format("Found block by color: %s", foundBlock.get().getColor()));
            System.out.println(String.format("Material: %s", foundBlock.get().getMaterial()));

        } else {
            System.out.println("Block with specified color not found.");
        }

        List<Block> foundBlocks = wall.findBlocksByMaterial("Wood");
        System.out.println("Found blocks by material:");
        foundBlocks.forEach(block -> {
            System.out.println(String.format("Color: %s", block.getColor()));
            System.out.println(String.format("Material: %s", block.getMaterial()));
        });

        System.out.println(wall.count());
    }
}