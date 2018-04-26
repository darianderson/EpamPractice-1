package ua.nure.kolesnikov.module4;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class Anotations {
	public static void main(String[] args) throws Exception {
		Class<A> c = A.class;

		Method[] methods = c.getDeclaredMethods();
		for (Method m : methods) {
			System.out.println(m);

			G g = m.getAnnotation(G.class);

			System.out.println(g.getClass().getSuperclass());
			System.out.println(g.getClass().getInterfaces()[0]);

			if (g != null) {
				System.out.println(g.x());
			}

			System.out.println("~~~");
		}

	}
}

class A {
	@G(x = 7)
	void m() {
	}

	@G(x = 100)
	void m2() {
	}

	@G
	void m3() {
	}
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface G {
	int x() default -1;
}
