package ru.nsu.ccfit.zuev.osu.game;

import android.graphics.PointF;

import ru.nsu.ccfit.zuev.osu.scoring.Replay.ReplayObjectData;

public abstract class GameObject {
    //tzl: 和游戏逻辑相关的描述游戏元素的抽象类
    protected boolean endsCombo;//tzl: 是否断combo
    protected boolean autoPlay = false;//tzl: 是否自动
    protected float hitTime = 0;//tzl: hit这个对象的时间?为什么是float?
    protected int id = -1;//tzl: id从几开始，由谁管理?
    protected ReplayObjectData replayData = null;//tzl: 蛤?在这里拿Replay类的静态内部类来用?这样设计合理吗?
    protected boolean startHit = false;//tzl: 是否已经开始了hit?
    protected PointF pos = new PointF();//tzl: 位置，原点在左上，x和y是float类型的

    public ReplayObjectData getReplayData() {
        return replayData;
    }

    public void setReplayData(ReplayObjectData replayData) {
        this.replayData = replayData;
    }

    public void setEndsCombo(final boolean endsCombo) {
        this.endsCombo = endsCombo;
    }

    public void setAutoPlay() {
        autoPlay = true;
    }

    public abstract void update(float dt);

    public float getHitTime() {
        return hitTime;
    }

    public void setHitTime(final float hitTime) {
        this.hitTime = hitTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStartHit(){
        return startHit;
    }

    public void tryHit(float dt) {return;};

    public PointF getPos() {return pos;};
}
