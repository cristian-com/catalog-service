package com.cristian.catalog.commands;

import java.util.List;
import java.util.Set;

public record CreateItemCommand(String organization, String name, String category, String description,
                                Set<String> tags, List<String> images) implements Command {
}
