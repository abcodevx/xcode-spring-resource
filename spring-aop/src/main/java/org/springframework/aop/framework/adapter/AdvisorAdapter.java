/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.aop.framework.adapter;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import org.springframework.aop.Advisor;

/**
 * 允许扩展 Spring AOP 框架的接口
 * 处理新的顾问和建议类型。
 *
 * <p>实现对象可以创建AOP拦截器
 * 自定义建议类型，使这些建议类型能够被使用
 * 在 Spring AOP 框架中，它在幕后使用拦截。
 *
 * <p>大多数Spring用户没有必要实现这个接口；
 * 仅当您需要向 Spring 引入更多 Advisor 或 Advice 类型时才这样做。
 *
 * @author Rod Johnson
 */
public interface AdvisorAdapter {

    /**
     * 该适配器是否理解该建议对象？是否有效
     * 使用 Advisor 调用 {@code getInterceptors} 方法
     * 包含此建议作为参数吗？
     *
     * @param advice an Advice such as a BeforeAdvice
     * @return whether this adapter understands the given advice object
     * @see #getInterceptor(org.springframework.aop.Advisor)
     * @see org.springframework.aop.BeforeAdvice
     */
    boolean supportsAdvice(Advice advice);

    /**
     * Return an AOP Alliance MethodInterceptor exposing the behavior of
     * the given advice to an interception-based AOP framework.
     * <p>Don't worry about any Pointcut contained in the Advisor;
     * the AOP framework will take care of checking the pointcut.
     *
     * @param advisor the Advisor. The supportsAdvice() method must have
     *                returned true on this object
     * @return an AOP Alliance interceptor for this Advisor. There's
     * no need to cache instances for efficiency, as the AOP framework
     * caches advice chains.
     */
    MethodInterceptor getInterceptor(Advisor advisor);

}
