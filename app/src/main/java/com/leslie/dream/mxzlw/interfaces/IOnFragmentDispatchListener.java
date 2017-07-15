package com.leslie.dream.mxzlw.interfaces;

/**
 * fragment 通信接口
 * 
 * @Author dzl on 2017/7/4.
 *
 */
public interface IOnFragmentDispatchListener {

	/**
	 * 交互数据
	 */
	public Object onDispatchData(int what, Object... obj);

}
