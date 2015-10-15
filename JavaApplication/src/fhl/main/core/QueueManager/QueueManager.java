/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fhl.main.core.QueueManager;

import fhl.main.core.queues.BalanceDataQueue;
import fhl.main.core.queues.CandleDataQueue;
import fhl.main.core.queues.IBaseQueue;
import fhl.main.core.queues.NewsDataQueue;
import fhl.main.core.queues.TickDataQueue;
import fhl.main.core.queues.TradeDataQueue;
import fhl.main.core.queues.TradesDataQueue;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filip
 */
public class QueueManager {
    private final List<IBaseQueue> queueList;
    private boolean isAdded;
    public QueueManager()
    {
        this.queueList = new ArrayList<>();
    }
    public void initStreamingQueues()
    {
        this.addQueue(new BalanceDataQueue());
        this.addQueue(new CandleDataQueue());
        this.addQueue(new NewsDataQueue());
        this.addQueue(new TickDataQueue());
        this.addQueue(new TradeDataQueue());
        this.addQueue(new TradesDataQueue());
    }
    public IBaseQueue getQueue(String queueName)
    {
        int i =0;
        while(i<this.queueList.size())
        {
            if(this.queueList.get(i).getClass().getName().contains(queueName))
            {
                return this.queueList.get(i);
            }
            i++;
        }
        return null;
    }
    public boolean addQueue(IBaseQueue queue)
    {
        String className = queue.getClass().getName();
        this.isAdded = true;
        this.queueList.forEach((IBaseQueue iQueue) -> {
            if(iQueue.getClass().getName().equals(className) )
            {
                this.isAdded = false;
            }
        });
        if(this.isAdded == true)
        {
            this.queueList.add(queue);
        }
        return isAdded;
    };
    public boolean removeQueue(IBaseQueue queue)
    {
        return this.queueList.remove(queue);
    }
}
