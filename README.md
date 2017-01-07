# LifeCycler<br>
### ps：我仔细想了下，其实一般在写链式调用的时候，只需要extend LifeCycler这个类，在对应的方法里面增加addOnActivityResumedListener（）这个回调，在对应生命周期函数进行cancle或者其他操作即可。如果生硬的在绑定的activity或者fragment中去处理，无疑增加了代码的耦合度。2017-1-7 22:42:27
##简介：<br>
作为任意一个持有context的链式回调的代码，特别是包含异步请求，无可避免的会对activity or fragment 持有。造成不必要的潜在内存泄露的代码。因此我想做的事是：在任意链式回调bind这个lifecycle的library，在特定的生命周期例如：destroy，pause做暂停或者终止请求的操作。类似Rxlifecycle，compose(this.bindUntilEvent(ActivityEvent.PAUSE)) 的调用。<br>

##用法：<br>
####单独使用LifeCycler绑定当前activity的用法：
```java
new LifeCycler().with(this)
                .addOnActivityResumedListener(new Func1() {
                    @Override
                    public void run(Activity activity) {
                        //do what you want to do in onResume
                    }
                })
                .addOnActivityPausedListener(new Func1() {
                    @Override
                    public void run(Activity activity) {
                        //do what you want to do in onPause
                    }
                })
                .addOnActivityDestroyedListener(new Func1() {
                    @Override
                    public void run(Activity activity) {
                        //do what you want to do in onDestroy
                    }
                });
```
##其他链式调用绑定context生命周期回调用法：<br>
只需要extend LifeCycler这个类即可。<br>
##申明：<br>
原作者：created by @Jween
