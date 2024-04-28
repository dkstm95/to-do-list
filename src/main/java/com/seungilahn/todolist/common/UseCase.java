package com.seungilahn.todolist.common;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface UseCase {

  /**
   * The value may indicate a suggestion for a logical component nickname,
   * to be turned into a Spring bean in case of an autodetected component.
   * @return the suggested component nickname, if any (or empty String otherwise)
   */
  @AliasFor(annotation = Component.class)
  String value() default "";

}
