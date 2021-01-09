package design.behavioral.interpreter.general;

import java.util.HashMap;

/**
 * 上下文环境类角色---包含解释器之外的全局信息。它的任务一般是用来存放文法中各个终结符所对应的具体的指，比如R=R1+R2，给R1
 * 赋值100，给R2赋值200，这些信息需要存放到环境中。
 */
public class Context extends HashMap {

}