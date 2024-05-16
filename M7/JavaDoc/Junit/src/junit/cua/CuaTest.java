package junit.cua;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CuaTest {
	@Test
	void test() throws Exception {
		Cua q = new Cua(6);
		assertEquals(true, q.esBuida());
		assertEquals(false, q.esPlena());
		q.encuar(10);
		assertEquals(false, q.esBuida());
		q.desencuar();
		assertEquals(true, q.esBuida());
		q.encuar(11);
		assertEquals(11, q.desencuar());
		q.encuar(12);
		q.encuar(13);
		assertEquals(12, q.desencuar());
		assertEquals(13, q.desencuar());
		assertEquals(true, q.esBuida());
		q.desencuar();
		q.desencuar();
		assertThrows(Exception.class, () -> {
			q.desencuar();
		}); // La cua és buida; desencuar ha de llançar una excepció
	}
}
