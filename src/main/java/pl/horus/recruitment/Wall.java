package pl.horus.recruitment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {

    private List<Block> blocks;

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return findBlockByColorRecursive(blocks, color);
    }

    private Optional<Block> findBlockByColorRecursive(List<Block> blocks, String color) {
        return blocks.stream()
                .filter(block -> block.getColor().equals(color))
                .findFirst()
                .or(() -> blocks.stream()
                        .filter(block -> block instanceof CompositeBlock)
                        .map(block -> findBlockByColorRecursive(((CompositeBlock) block).getBlocks(), color))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .findFirst());
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> resultBlocks = new ArrayList<>();
        blocks.forEach(block -> findBlocksByMaterialRecursive(block, material, resultBlocks));
        return resultBlocks;
    }

    private void findBlocksByMaterialRecursive(Block block, String material, List<Block> resultBlocks) {

        if (block.getMaterial().equals(material)) {
            resultBlocks.add(block);
        }
        if (block instanceof CompositeBlock compositeBlock) {
            compositeBlock.getBlocks()
                    .forEach(subBlock -> findBlocksByMaterialRecursive(subBlock, material, resultBlocks));
        }
    }

    public int count() {
        return countRecursiveV2(blocks);
    }

    //W zależności od oczekiwanego wyniku metoda countRecursive() uwzględnia faktyczną ilość obiektów
    //w strukturze tzn. jeden obiekt CompositeBlock z dwoma zagnieżdżonymi obiektami Block dają sumarycznie
    // 3 bloki. Metoda countRecursiveV2() zlicza tylko obiekty typu block.
    private int countRecursive(List<Block> blocks) {
        int totalCount = 0;
        for (Block block : blocks) {
            if (block instanceof CompositeBlock compositeBlock) {
                totalCount += countRecursive(compositeBlock.getBlocks());
            }
            totalCount++;
        }
        return totalCount;
    }

    private int countRecursiveV2(List<Block> blocks) {
        return blocks.stream()
                .mapToInt(block -> block instanceof CompositeBlock compositeBlock ? countRecursiveV2(compositeBlock.getBlocks()) : 1)
                .sum();
    }


}


