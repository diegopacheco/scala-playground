import { GraphQLService, SchemaChangeCallback, Unsubscriber, GraphQLServiceEngineConfig } from 'apollo-server-core';
import { GraphQLExecutionResult, GraphQLRequestContext, WithRequired } from 'apollo-server-types';
import { InMemoryLRUCache } from 'apollo-server-caching';
import { GraphQLSchema, GraphQLError } from 'graphql';
import { ServiceDefinition } from '@apollo/federation';
import { Logger } from 'loglevel';
import { buildQueryPlan, buildOperationContext } from './buildQueryPlan';
import { executeQueryPlan, ServiceMap } from './executeQueryPlan';
import { CompositionMetadata } from './loadServicesFromStorage';
import { serializeQueryPlan, QueryPlan, OperationContext } from './QueryPlan';
import { GraphQLDataSource } from './datasources/types';
import { HeadersInit } from 'node-fetch';
export declare type ServiceEndpointDefinition = Pick<ServiceDefinition, 'name' | 'url'>;
interface GatewayConfigBase {
    debug?: boolean;
    __exposeQueryPlanExperimental?: boolean;
    buildService?: (definition: ServiceEndpointDefinition) => GraphQLDataSource;
    experimental_didResolveQueryPlan?: Experimental_DidResolveQueryPlanCallback;
    experimental_didFailComposition?: Experimental_DidFailCompositionCallback;
    experimental_updateServiceDefinitions?: Experimental_UpdateServiceDefinitions;
    experimental_didUpdateComposition?: Experimental_DidUpdateCompositionCallback;
    experimental_pollInterval?: number;
}
interface RemoteGatewayConfig extends GatewayConfigBase {
    serviceList: ServiceEndpointDefinition[];
    introspectionHeaders?: HeadersInit;
}
interface ManagedGatewayConfig extends GatewayConfigBase {
    federationVersion?: number;
}
interface LocalGatewayConfig extends GatewayConfigBase {
    localServiceList: ServiceDefinition[];
}
export declare type GatewayConfig = RemoteGatewayConfig | LocalGatewayConfig | ManagedGatewayConfig;
declare type DataSourceCache = {
    [serviceName: string]: {
        url?: string;
        dataSource: GraphQLDataSource;
    };
};
export declare type Experimental_DidResolveQueryPlanCallback = ({ queryPlan, serviceMap, operationContext, }: {
    readonly queryPlan: QueryPlan;
    readonly serviceMap: ServiceMap;
    readonly operationContext: OperationContext;
}) => void;
export declare type Experimental_DidFailCompositionCallback = ({ errors, serviceList, compositionMetadata, }: {
    readonly errors: GraphQLError[];
    readonly serviceList: ServiceDefinition[];
    readonly compositionMetadata?: CompositionMetadata;
}) => void;
export interface Experimental_CompositionInfo {
    serviceDefinitions: ServiceDefinition[];
    schema: GraphQLSchema;
    compositionMetadata?: CompositionMetadata;
}
export declare type Experimental_DidUpdateCompositionCallback = (currentConfig: Experimental_CompositionInfo, previousConfig?: Experimental_CompositionInfo) => void;
export declare type Experimental_UpdateServiceDefinitions = (config: GatewayConfig) => Promise<{
    serviceDefinitions?: ServiceDefinition[];
    compositionMetadata?: CompositionMetadata;
    isNewSchema: boolean;
}>;
declare type RequestContext<TContext> = WithRequired<GraphQLRequestContext<TContext>, 'document' | 'queryHash'>;
export declare class ApolloGateway implements GraphQLService {
    schema?: GraphQLSchema;
    protected serviceMap: DataSourceCache;
    protected config: GatewayConfig;
    protected logger: Logger;
    protected queryPlanStore?: InMemoryLRUCache<QueryPlan>;
    private engineConfig;
    private pollingTimer?;
    private onSchemaChangeListeners;
    private serviceDefinitions;
    private compositionMetadata?;
    private serviceSdlCache;
    protected experimental_didResolveQueryPlan?: Experimental_DidResolveQueryPlanCallback;
    protected experimental_didFailComposition?: Experimental_DidFailCompositionCallback;
    protected experimental_didUpdateComposition?: Experimental_DidUpdateCompositionCallback;
    protected updateServiceDefinitions: Experimental_UpdateServiceDefinitions;
    protected experimental_pollInterval?: number;
    constructor(config?: GatewayConfig);
    load(options?: {
        engine?: GraphQLServiceEngineConfig;
    }): Promise<{
        schema: GraphQLSchema;
        executor: <TContext>(requestContext: WithRequired<GraphQLRequestContext<TContext>, "document" | "queryHash">) => Promise<GraphQLExecutionResult>;
    }>;
    protected updateComposition(options?: {
        engine?: GraphQLServiceEngineConfig;
    }): Promise<void>;
    protected createSchema(serviceList: ServiceDefinition[]): GraphQLSchema;
    onSchemaChange(callback: SchemaChangeCallback): Unsubscriber;
    private startPollingServices;
    private createAndCacheDataSource;
    protected createServices(services: ServiceEndpointDefinition[]): void;
    protected loadServiceDefinitions(config: GatewayConfig): ReturnType<Experimental_UpdateServiceDefinitions>;
    executor: <TContext>(requestContext: WithRequired<GraphQLRequestContext<TContext>, "document" | "queryHash">) => Promise<GraphQLExecutionResult>;
    protected validateIncomingRequest<TContext>(requestContext: RequestContext<TContext>, operationContext: OperationContext): readonly GraphQLError[];
    private initializeQueryPlanStore;
    stop(): Promise<void>;
}
export { buildQueryPlan, executeQueryPlan, serializeQueryPlan, buildOperationContext, QueryPlan, ServiceMap, };
export * from './datasources';
//# sourceMappingURL=index.d.ts.map