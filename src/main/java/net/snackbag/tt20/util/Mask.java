package net.snackbag.tt20.util;

import com.google.gson.JsonElement;
//? if >=1.21.11 {
/*import net.minecraft.resources.Identifier;
*///?} else {
import net.minecraft.resources.ResourceLocation;
//?}
import net.minecraftforge.registries.IForgeRegistry;
import net.snackbag.tt20.TT20;
import net.snackbag.tt20.config.JSONConfiguration;

import java.util.*;
import java.util.stream.Collectors;

public class Mask {
    private final JSONConfiguration file;
    private final MaskType maskType;
    private final IForgeRegistry<?> registry;
    //? if >=1.21.11 {
    /*private final Set<Identifier> entries;
    *///?} else {
    private final Set<ResourceLocation> entries;
    //?}

    public Mask(IForgeRegistry<?> registry, JSONConfiguration file, String maskKey) {
        this.file = file;
        this.maskType = MaskType.fromString(file.getAsString("type"));
        this.registry = registry;
        this.entries = new HashSet<>();

        for (JsonElement element : file.getAsArray(maskKey)) {
            if (!(element.isJsonPrimitive() && element.getAsJsonPrimitive().isString())) {
                TT20.LOGGER.error("(TT20) Mask element '" + element + "' isn't a string");
                return;
            }

            entries.addAll(manageEntry(element.getAsString()));
        }
    }

    //? if >=1.21.11 {
    /*private List<Identifier> manageEntry(String entry) {
    *///?} else {
    private List<ResourceLocation> manageEntry(String entry) {
     //?}
        String[] split = entry.split(":");

        if (split.length != 2) {
            TT20.LOGGER.error("(TT20) '" + entry + "' is not a valid identifier. Correct format is <namespace>:<path>");
            return Collections.emptyList();
        }

        final String targetNamespace = split[0];
        final String targetPath = split[1];

        return registry.getEntries().stream()
            //? if >=1.21.11 {
            /*.map(registryEntry -> registryEntry.getKey().registry())
            *///?} else {
            .map(registryEntry -> registryEntry.getKey().location())
            //?}
                .filter(location ->
                        (targetNamespace.equals("*") || location.getNamespace().equals(targetNamespace)) &&
                                (targetPath.equals("*") || location.getPath().equals(targetPath))
                )
                .collect(Collectors.toList());
    }

    public IForgeRegistry<?> getRegistry() {
        return registry;
    }

    public JSONConfiguration getFile() {
        return file;
    }

    //? if >=1.21.11 {
    /*public boolean matches(Identifier identifier) {
    *///?} else {
    public boolean matches(ResourceLocation identifier) {
    //?}
        return entries.contains(identifier);
    }

    //? if >=1.21.11 {
    /*public boolean isOkay(Identifier identifier) {
    *///?} else {
    public boolean isOkay(ResourceLocation identifier) {
     //?}
        return (maskType == MaskType.WHITELIST) == entries.contains(identifier);
    }
}