package junit.cua;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CuaTest {
	@Test
	void test() throws Exception {
		Cua q = new Cua(6);
		assertEquals(true, q.esBuida());
		q.encuar(10);
		assertEquals(false, q.esBuida());
		q.desencuar();
		q.encuar(11);
		assertEquals(11, q.desencuar());
		q.encuar(12);
		q.encuar(13);
		q.encuar(14);
		q.encuar(15);
		assertEquals(12, q.desencuar());
		assertEquals(13, q.desencuar());
		q.encuar(16);
		q.encuar(17);
		q.encuar(18);
		q.encuar(19);
		assertEquals(true, q.esPlena());
		assertThrows(Exception.class, () -> {
			q.encuar(56);
		});
		q.desencuar();
		q.desencuar();
		q.desencuar();
		q.desencuar();
		q.desencuar();
		q.desencuar();
		assertEquals(true, q.esBuida());
		assertThrows(Exception.class, () -> {
			q.desencuar();
		});
	}
}
