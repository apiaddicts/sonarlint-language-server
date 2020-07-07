/*
 * SonarLint Language Server
 * Copyright (C) 2009-2020 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonarsource.sonarlint.ls.settings;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import javax.annotation.concurrent.Immutable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.sonarsource.sonarlint.core.client.api.common.RuleKey;

/**
 * Settings global to the entire workspace (user + machine + workspace scopes)
 */
@Immutable
public class WorkspaceSettings {

  private final boolean disableTelemetry;
  private final Map<String, ServerConnectionSettings> servers;
  private final Collection<RuleKey> excludedRules;
  private final Collection<RuleKey> includedRules;
  private final Map<RuleKey, Map<String, String>> ruleParameters;
  private final boolean showAnalyzerLogs;
  private final boolean showVerboseLogs;

  public WorkspaceSettings(boolean disableTelemetry, Map<String, ServerConnectionSettings> servers,
    Collection<RuleKey> excludedRules, Collection<RuleKey> includedRules, Map<RuleKey, Map<String, String>> ruleParameters,
    boolean showAnalyzerLogs, boolean showVerboseLogs) {
    this.disableTelemetry = disableTelemetry;
    this.servers = servers;
    this.excludedRules = excludedRules;
    this.includedRules = includedRules;
    this.ruleParameters = ruleParameters;
    this.showAnalyzerLogs = showAnalyzerLogs;
    this.showVerboseLogs = showVerboseLogs;
  }

  public boolean isDisableTelemetry() {
    return disableTelemetry;
  }

  public Map<String, ServerConnectionSettings> getServers() {
    return Collections.unmodifiableMap(servers);
  }

  public Collection<RuleKey> getExcludedRules() {
    return excludedRules;
  }

  public Collection<RuleKey> getIncludedRules() {
    return includedRules;
  }

  public Map<RuleKey, Map<String, String>> getRuleParameters() {
    return ruleParameters;
  }

  public boolean hasLocalRuleConfiguration() {
    return !excludedRules.isEmpty() || !includedRules.isEmpty();
  }

  public boolean showAnalyzerLogs() {
    return showAnalyzerLogs;
  }

  public boolean showVerboseLogs() {
    return showVerboseLogs;
  }

  @Override
  public int hashCode() {
    return Objects.hash(disableTelemetry, servers, excludedRules, includedRules, showAnalyzerLogs, showVerboseLogs);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    WorkspaceSettings other = (WorkspaceSettings) obj;
    return disableTelemetry == other.disableTelemetry && Objects.equals(servers, other.servers) && Objects.equals(excludedRules, other.excludedRules)
      && Objects.equals(includedRules, other.includedRules) && Objects.equals(ruleParameters, other.ruleParameters)
      && Objects.equals(showAnalyzerLogs, other.showAnalyzerLogs) && Objects.equals(showVerboseLogs, other.showVerboseLogs);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

}
