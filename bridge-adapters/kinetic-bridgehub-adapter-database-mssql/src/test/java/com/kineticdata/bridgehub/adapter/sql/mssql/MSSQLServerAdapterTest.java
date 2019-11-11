package com.kineticdata.bridgehub.adapter.sql.mssql;

import com.kineticdata.bridgehub.adapter.BridgeAdapter;
import com.kineticdata.bridgehub.adapter.BridgeAdapterTestBase;
import com.kineticdata.bridgehub.adapter.BridgeError;
import com.kineticdata.bridgehub.adapter.BridgeRequest;
import com.kineticdata.bridgehub.adapter.Count;
import com.kineticdata.bridgehub.adapter.Record;
import com.kineticdata.bridgehub.adapter.RecordList;
import java.util.Arrays;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;

public class MSSQLServerAdapterTest extends BridgeAdapterTestBase{
    @Override
    public String getConfigFilePath() {
        return "src/test/resources/bridge-config.yml";
    }
    
    @Override
    public Class getAdapterClass() {
        return MSSQLServerAdapter.class;
    }
    
    @Test
    public void test_count() throws Exception {
        BridgeRequest request = new BridgeRequest();
        request.setStructure("Inventory");
        request.setQuery("");
        
        List<String> list = Arrays.asList();
        request.setFields(list);
        
        Count count = getAdapter().count(request);
        Assert.assertTrue(count.getValue() > 0);
    }
    
    @Test
    public void test_retrieve() throws Exception {
        BridgeRequest request = new BridgeRequest();
        request.setStructure("Inventory");
        request.setQuery("id=5");
        
        List<String> list = Arrays.asList("name");
        request.setFields(list);
        
        Record record = getAdapter().retrieve(request);
        Assert.assertTrue(record.getRecord().size() > 0);
    }
    
    @Test
    public void test_search() throws Exception {
        BridgeRequest request = new BridgeRequest();
        request.setStructure("Inventory");
        request.setQuery("quantity >= 150");
        
        List<String> list = Arrays.asList("name");
        request.setFields(list);
        
        RecordList records = getAdapter().search(request);
        Assert.assertTrue(records.getRecords().size() > 0);
    }
}
