/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fatal1t.forexapp.spring.api.adapters.requests;

import org.fatal1t.forexapp.spring.api.eventdata.NewTradeRecord;
import pro.xstore.api.message.codes.TRADE_OPERATION_CODE;
import pro.xstore.api.message.codes.TRADE_TRANSACTION_TYPE;
import pro.xstore.api.message.records.TradeTransInfoRecord;

/**
 *
 * @author fatal1t
 */
public class TradeTransactionReq {
    private NewTradeRecord newTradeRecord;

    public TradeTransactionReq() {
    }

    public TradeTransactionReq(NewTradeRecord newTradeRecord) {
        this.newTradeRecord = newTradeRecord;
    }

    public NewTradeRecord getNewTradeRecord() {
        return newTradeRecord;
    }

    public void setNewTradeRecord(NewTradeRecord newTradeRecord) {
        this.newTradeRecord = newTradeRecord;
    }
    
    
    
    private TradeTransInfoRecord getAPIRecord()
    {
        /**
         * cmd	Number	Operation code
            customComment	String	The value the customer may provide in order to retrieve it later.
            expiration	Time	Pending order expiration time
            offset	Number	Trailing offset
            order	Number	0 or position number for closing/modifications
            price	Floating number	Trade price
            sl	Floating number	Stop loss
            symbol	String	Trade symbol
            tp	Floating number	Take profit
            type	Number	Trade transaction type
            volume	Floating number	Trade volume
         */
        /**cmd field 
         * BUY	0	buy
         * SELL	1	sell
         * BUY_LIMIT	2	buy limit
         * SELL_LIMIT	3	sell limit
         * BUY_STOP	4	buy stop
         * SELL_STOP	5	sell stop
         * BALANCE	6	Read only. Used in getTradesHistory for manager's deposit/withdrawal operations (profit>0 for deposit,  0 > profit for withdrawal).
         * CREDIT	7	Read only
         * 
         * type field
         * OPEN	0	order open, used for opening orders
         * PENDING	1	order pending, only used in the streaming getTrades command
         * LOSE	2	order close
         * MODIFY	3	order modify, only used in the tradeTransaction command
         * DELETE	4	order delete, only used in the tradeTransaction command

         */
        TRADE_OPERATION_CODE tradeCommand = null;
        TRADE_TRANSACTION_TYPE tradeTransactionType = null;
        String cmd = this.newTradeRecord.getCmd();
        String type = this.newTradeRecord.getType();
        if(cmd.equals("buy"))
        {
            tradeCommand = TRADE_OPERATION_CODE.BUY;
        }
        else if(cmd.equals("sell"))
        {
            tradeCommand = TRADE_OPERATION_CODE.SELL;
        }
        else if(cmd.equals("buy_limit"))
        {
            tradeCommand = TRADE_OPERATION_CODE.BUY_LIMIT;
        }
        else if(cmd.equals("sell_limit"))
        {
            tradeCommand = TRADE_OPERATION_CODE.SELL_LIMIT;
        }
        else if(cmd.equals("buy_stop"))
        {
            tradeCommand = TRADE_OPERATION_CODE.BUY_STOP;
        }
        else if(cmd.equals("sell_stop"))
        {
            tradeCommand = TRADE_OPERATION_CODE.SELL_STOP;
        }
        else 
        {
            return null;
        }
        
        if(type.equals("open"))
        {
            tradeTransactionType = TRADE_TRANSACTION_TYPE.OPEN;
        }
        else if (type.equals("close"))
        {
            tradeTransactionType = TRADE_TRANSACTION_TYPE.CLOSE;    
        }
        else
        {
            return null;
        }
        
        TradeTransInfoRecord command = new TradeTransInfoRecord(tradeCommand, tradeTransactionType, 
                this.newTradeRecord.getPrice(), this.newTradeRecord.getSl(),
                this.newTradeRecord.getTp(), this.newTradeRecord.getSymbol(),
                this.newTradeRecord.getVolume(), this.newTradeRecord.getOrder(),
                this.newTradeRecord.getCustomComment(), this.newTradeRecord.getExpiration());

            return command;
    }
}
