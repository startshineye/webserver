package com.yxm.junit;

import static org.junit.Assert.*;

import org.junit.Test;

public class MathTest {
   @Test
   public void testSum(){
	   int actual = Math.sum(10, 20);//实际值
	   int expected = 30;//期望值
	   assertEquals(expected,actual);//断言
   }
   @Test
   public void testDivide(){
	   int actual = Math.divide(100,5);//实际值
	   int expected = 20;//期望值
	   assertEquals(expected,actual);//断言
   }
}
