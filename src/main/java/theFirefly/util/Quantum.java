package theFirefly.util;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theFirefly.cards.AbstractFireflyCard;

public class Quantum extends DynamicVariable {

    @Override
    public String key() {
        return "quantum";
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractFireflyCard) card).isQuantumModified;
    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractFireflyCard) card).quantum;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractFireflyCard) card).baseQuantum;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractFireflyCard) card).upgradedQuantum;
    }
}