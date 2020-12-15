package day14;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Mask {

    BitSet ones = new BitSet(36);
    BitSet zeros = new BitSet(36);
    BitSet floatings = new BitSet(36);


    public void setIndexOne(int...indices) {
        for (int i : indices) ones.set(i);
    }

    public void setIndexZero(int...indices) {
        for (int i : indices) zeros.set(i);
    }

    public void setIndexFloating(int...indices) {
        for (int i : indices) floatings.set(i);
    }

    public void resetMask() {
        ones.clear();
        zeros.clear();
        floatings.clear();
    }

    public long applyValue(long value) {
        BitSet bitSet = BitSet.valueOf(new long[]{value});
        for (int i = 0; i < 36; i++) {
            if (ones.get(i)) bitSet.set(i, true);
            if (zeros.get(i)) bitSet.set(i, false);
        }
        return bitSet.toLongArray()[0];
    }

    public List<Long> applyAddress(long address) { ;
        BitSet bitSet = BitSet.valueOf(new long[]{address});
        for (int i = 0; i < 36; i++) {
            if (ones.get(i)) bitSet.set(i, true);
            else if (floatings.get(i)) bitSet.set(i, false);
        }
        List<Long> addresses = new ArrayList<>();
        findAllFloatings(bitSet, 0, addresses);
        return addresses;
    }

    private void findAllFloatings(BitSet bitSet, int index, List<Long> addresses) {
        BitSet option1 = (BitSet) bitSet.clone();
        BitSet option2 = (BitSet) bitSet.clone();

        int nextFloatingIndex = floatings.nextSetBit(index);
        if (nextFloatingIndex == -1) {
            addresses.add(bitSet.toLongArray()[0]);
            return;
        }
        option1.set(nextFloatingIndex, true);
        option2.set(nextFloatingIndex, false);
        findAllFloatings(option1, nextFloatingIndex+1, addresses);
        findAllFloatings(option2, nextFloatingIndex+1, addresses);
    }
}