package bycode.asm;

/**
 * 利用ASM的CoreAPI来增强类。这里不纠结于AOP的专业名词如切片、通知，只实现在方法调用前、后增加逻辑，通俗易懂且方便理解。
 * 首先定义需要被增强的Base类：其中只包含一个process()方法，方法内输出一行“process”。
 * 增强后，我们期望的是，方法执行前输出“start”，之后输出”end”。
 */
public class Base {
    public void process(){
        System.out.println("process");
    }
}
