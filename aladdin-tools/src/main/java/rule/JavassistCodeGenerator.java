package rule;

import javassist.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class JavassistCodeGenerator {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, CannotCompileException, NotFoundException, NoSuchMethodException {
        JavassistCodeGenerator javassistCodeGenerator = new JavassistCodeGenerator();
        javassistCodeGenerator.codeGen("instOrgCode == alipay");
    }

    AbstractExpression codeGen(String expression) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        String[] split = expression.split("==");
        String key = split[0];
        String value = split[1];

        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass(this.getClass().getPackage().getName() + ".$4324235");
        ctClass.setSuperclass(classPool.get(AbstractExpression.class.getName()));


        //添加自定义方法
        ctClass.addMethod(CtMethod.make("public boolean execute0(java.util.Map env) { " +
                "System.out.println(env.toString()+key + env.keySet().toString()); return true;}", ctClass));


        Class<?> clazz = ctClass.toClass();
        AbstractExpression abstractExpression = (AbstractExpression) clazz.newInstance();
        for (Method method : abstractExpression.getClass().getMethods()) {
            System.out.println(method.getName());
        }

        abstractExpression.setKey(key);
        abstractExpression.setValue(value);
        HashMap<String, String> env = new HashMap<>();
        env.put("name", "shixu");
        env.put("instOrgCode", "alipay");
        System.out.println(abstractExpression.execute(env));

        return null;
    }
}
