package pl.horus.recruitment;

import java.util.List;

public class CompositeBlockImpl implements CompositeBlock {
    public CompositeBlockImpl(List<Block> blocks) {
        this.blocks = blocks;
    }

    List<Block> blocks;

    @Override
    public String getColor(){
        return "Multi-colored";
    }

    @Override
    public String getMaterial() {
        return "Multi-material";
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }
}
