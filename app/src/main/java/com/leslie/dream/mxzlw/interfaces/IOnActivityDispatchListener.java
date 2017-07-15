package com.leslie.dream.mxzlw.interfaces;

/**
 * activity 通信接口
 * 
 * @Author dzl on 2017/7/4.
 *
 */
public interface IOnActivityDispatchListener {

	/**
	 * 交互数据
	 */
	public Object onDispatchData(int what, Object... obj);

}
