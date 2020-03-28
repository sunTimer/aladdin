package bycode.javassit;

import bycode.asm.Base;
import javassist.*;

import java.io.IOException;

public class JavassitCode {

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException, IOException {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("bycode.asm.Base");
        CtMethod m = cc.getDeclaredMethod("process");
        m.insertBefore("{ System.out.println(\"start\"); }");
        m.insertAfter("{ System.out.println(\"end---\"); }");
        m.insertAfter("{ System.out.println(\"end---\"); }");
        System.out.println(m.getName());
        Class c = cc.toClass();
        cc.writeFile("/Users/shixu/IdeaProjects/aladdin/aladdin-tools/target/classes/");
        Base h = (Base) c.newInstance();
        h.process();
    }
}

