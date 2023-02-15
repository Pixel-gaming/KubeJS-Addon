package com.c0d3m4513r.KubeJS_addons;

import net.minecraftforge.server.permission.nodes.PermissionDynamicContextKey;
import net.minecraftforge.server.permission.nodes.PermissionNode;
import net.minecraftforge.server.permission.nodes.PermissionType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public final class KubeJSAddons {
    // Forge Permission API uses the raw types, and I want to be 100% compatible with it, so I'm using raw types here too
    @SuppressWarnings("rawtypes")
    public static <T> PermissionNode<T> constructPermissionNode(String modId, String permission, PermissionType<T> type, PermissionNode.PermissionResolver<T> resolver, PermissionDynamicContextKey... context){
        return new PermissionNode<>(modId, permission, type, resolver, context);
    }

    public static <T> PermissionDynamicContextKey<T> constructPermissionDynamicContextKey(Class<T> type, String key, Function<T, String> resolver){
        return new PermissionDynamicContextKey<>(type,key, resolver);
    }

    @Contract(value = "_, _, _ -> param2", pure = true)
    public static <T> T try_catch(@NotNull Supplier<T> supplier, @NotNull Function<Exception, Boolean> handleConditional, @NotNull Function<Exception, T> handle){
        try{
            return supplier.get();
        }catch(Exception e){
            if (handleConditional.apply(e))
                return handle.apply(e);
            else
                throw e;
        }
    }

    @Contract(value = "_ -> fail", pure = true)
    public static <R, T extends Throwable> R throw_e(@NotNull T t) throws T {
        throw t;
    }
}
