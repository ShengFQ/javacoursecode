package com.geekbang.shengfq.week0.design.chain01.abs;

/**
 * 处理者共同的的模板
 *
 * @author shengfq
 * @date 2021-05-19
 */
public abstract class BaseHandler implements Handler{

    Handler next;
    /**
     * 设置 处理链的下个处理者
     *
     * @param handler
     */
    @Override
    public void setNext(Handler handler) {
        this.next=handler;
    }

    /**
     * 请求处理
     *
     * @param request
     */
    @Override
    public void handle(Request request) {
        //责任链的关键所在,设置了下一个处理器引用,默认就跑下一个handle
        if(next!=null){
            next.handle(request);
        }
    }
    /**
     * 处理器前置通知
     * */
   public abstract void preHandle();
    /**
     * 处理器后置通知
     * */
  public  abstract void afterHandle();
  /**
   * 是否能处理
   * */
  public abstract boolean canHandle(Request request);
}
