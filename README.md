# LifeCycler
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
####其他链式调用绑定lifecycle的用法：
```java
  改天我在贴出来，今天先到这里
```
##申明：<br>
原作者：created by @Jween
