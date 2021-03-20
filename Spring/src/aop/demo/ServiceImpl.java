package aop.demo;

import org.springframework.stereotype.Service;

/**
 * @author BigRedCaps
 * @date 2021/3/20 11:31
 */
@Service("service")
public class ServiceImpl implements IService
{
    @Override
    public void doService ()
    {
        System.out.println("do service......");
    }
}
