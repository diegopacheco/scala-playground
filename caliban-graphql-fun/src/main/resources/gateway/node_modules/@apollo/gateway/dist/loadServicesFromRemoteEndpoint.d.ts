import { HeadersInit } from 'node-fetch';
import { GraphQLDataSource } from './datasources/types';
import { Experimental_UpdateServiceDefinitions } from './';
export declare function getServiceDefinitionsFromRemoteEndpoint({ serviceList, headers, serviceSdlCache, }: {
    serviceList: {
        name: string;
        url?: string;
        dataSource: GraphQLDataSource;
    }[];
    headers?: HeadersInit;
    serviceSdlCache: Map<string, string>;
}): ReturnType<Experimental_UpdateServiceDefinitions>;
//# sourceMappingURL=loadServicesFromRemoteEndpoint.d.ts.map