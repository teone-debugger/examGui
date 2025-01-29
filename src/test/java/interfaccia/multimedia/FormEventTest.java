package test.java.interfaccia.multimedia;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

interface FormListenerTest {
    void formEvent(FormEventTest fe);
}

class FormEventTest {
    private Object source;

    public FormEventTest(Object source) {
        this.source = source;
    }

    public Object getSource() {
        return source;
    }

    @Test
    public void testFormEvent() {
        FormListenerTest listener = new FormListenerTest() {
            @Override
            public void formEvent(FormEventTest fe) {
                assertNotNull(fe);
            }
        };

        FormEventTest event = new FormEventTest(this);
        listener.formEvent(event);
    }
}