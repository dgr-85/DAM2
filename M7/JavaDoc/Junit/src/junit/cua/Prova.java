package junit.cua;

public class Prova {
	public static void main(String[] args) {
		try {
			Cua q = new Cua(3);
			q.encuar(5);
			q.encuar(6);
			System.out.println(q.desencuar());
			q.encuar(7);
			System.out.println(q.desencuar());
			System.out.println(q.desencuar());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Cua q2 = new Cua(5);
			q2.encuar(1);
			q2.encuar(2);
			q2.encuar(3);
			q2.encuar(4);
			q2.encuar(5);
			System.out.println(q2.desencuar());
			System.out.println(q2.desencuar());
			q2.encuar(6);
			q2.encuar(7);
			System.out.println(q2.desencuar());
			System.out.println(q2.desencuar());
			System.out.println(q2.desencuar());
			System.out.println(q2.desencuar());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
