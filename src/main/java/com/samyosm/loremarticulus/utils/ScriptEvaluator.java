package com.samyosm.loremarticulus.utils;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeJSON;
import org.mozilla.javascript.ScriptableObject;

public class ScriptEvaluator {

    Context context;
    ScriptableObject scope;

    public ScriptEvaluator() {
        this.context = Context.enter();
        this.scope = context.initStandardObjects();
    }

    public Object parseJson(String json) {
        return NativeJSON.parse(context, scope, json, (context1, scriptable, scriptable1, objects) -> objects[1]);
    }

    public String evaluateString(String script, String functionName, Object... args) {
        context.evaluateString(scope, script, "script", 1, null);
        Function makeQuery = (Function) scope.get(functionName, scope);

        Object result = makeQuery.call(context, scope, scope, args);
        return (String) Context.jsToJava(result, String.class);
    }

    public void close() {
        context.close();
    }
}
