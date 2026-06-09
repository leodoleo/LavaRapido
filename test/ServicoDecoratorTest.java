import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicoDecoratorTest {

    @Test
    public void deveRetornarValorOriginal() {

        Servico s = new Servico(
                1,
                "Lavagem Completa",
                50.0);

        IServico servico = new ServicoBase(s);

        assertEquals(50.0, servico.getValor(), 0.01);
    }

    @Test
    public void deveAdicionarCera() {

        Servico s = new Servico(
                1,
                "Lavagem Completa",
                50.0);

        IServico servico =
                new CeraDecorator(
                        new ServicoBase(s));

        assertEquals(70.0, servico.getValor(), 0.01);
    }

    @Test
    public void deveAdicionarPolimento() {

        Servico s = new Servico(
                1,
                "Lavagem Completa",
                50.0);

        IServico servico =
                new PolimentoDecorator(
                        new ServicoBase(s));

        assertEquals(100.0, servico.getValor(), 0.01);
    }

    @Test
    public void deveAdicionarCeraEPolimento() {

        Servico s = new Servico(
                1,
                "Lavagem Completa",
                50.0);

        IServico servico =
                new PolimentoDecorator(
                        new CeraDecorator(
                                new ServicoBase(s)));

        assertEquals(120.0, servico.getValor(), 0.01);
    }
}